package machinecoding.auctionsystem.service;

import machinecoding.auctionsystem.exception.InvalidBidException;
import machinecoding.auctionsystem.models.Bid;
import machinecoding.auctionsystem.models.Item;
import machinecoding.auctionsystem.models.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BidTrackerImpl implements BidTracker {

    private final Map<Item, List<Bid>> auctionBoard;

    public BidTrackerImpl() {
        auctionBoard = new ConcurrentHashMap<>();
    }

    public Map<Item, List<Bid>> getCurrentAuctionBoardCopy() {
        return new HashMap<>(auctionBoard);
    }

    @Override
    public void recordBidOnItem(Bid bid, Item item) throws InvalidBidException {
        Objects.requireNonNull(bid);              // Replacement for validateBid(bid);
        Objects.requireNonNull(bid.getUser());    // Replacement for validateBid(bid);
        Objects.requireNonNull(item);             // Replacement for validateItem(item);
        recordBidOnItemSync(bid, item);
    }

    // synchronized method ensuring that only one bid is processed at a time.
    private synchronized void recordBidOnItemSync(Bid bid, Item item) throws InvalidBidException {
        ensureBidHigher(bid, item);
        addBidOnItem(item, bid);
    }

    private void ensureBidHigher(Bid bid, Item item) throws InvalidBidException {
        Optional<Bid> winningBidForItem = getWinningBidForItem(item);
        if (winningBidForItem.isPresent() && bid.getValue() <= winningBidForItem.get().getValue()) {
            throw new InvalidBidException(String.format(
                    "A bid of £%s on item %s is too low. It should be more than the current winning bid: £%s)",
                    bid.getValue(),
                    item,
                    winningBidForItem.get().getValue()));
        }
    }

    @Override
    public Optional<Bid> getWinningBidForItem(Item item) {
        List<Bid> bidList = getBidsForItem(item);
        return bidList.isEmpty() ? Optional.empty() : Optional.of(bidList.get(bidList.size() - 1));      // Winning Bid is the Last valid bid for a given item
    }

    @Override
    public List<Bid> getBidsForItem(Item item) {
        Objects.requireNonNull(item);   // Replacement for validateItem(item);
        return auctionBoard.getOrDefault(item, new ArrayList<>());
    }

    @Override
    public Set<Item> getItemsWithBidFromUser(User user) {
        return auctionBoard.entrySet().stream()
                .filter(bid -> containsBidFromUser(bid.getValue(), user))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }


    /* Utility methods */

    private boolean containsBidFromUser(List<Bid> bidsList, User user) {
        return bidsList.stream().anyMatch(bid -> bid.isFromUser(user));
    }

    private void addBidOnItem(Item item, Bid bid) {
        List<Bid> bidsOnItem = auctionBoard.getOrDefault(item, new ArrayList<>());
        bidsOnItem.add(bid);
        auctionBoard.put(item, bidsOnItem);

        // Above can be replaced with
        // auctionBoard.computeIfAbsent(item, ignored -> new ArrayList<>()).add(bid);
    }


    // Not used anymore : Replaced with Objects.requireNonNull
    private void validateItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item can't be null");
    }

    // Not used anymore : Replaced with Objects.requireNonNull
    private void validateBid(Bid bid) {
        if (bid == null)
            throw new IllegalArgumentException("Bid can't be null");
        if (bid.getUser() == null)
            throw new IllegalArgumentException("Bid's user can't be null");
    }
}