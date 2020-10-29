package machinecoding.auctionsystem.service;

import machinecoding.auctionsystem.exception.InvalidBidException;
import machinecoding.auctionsystem.models.Bid;
import machinecoding.auctionsystem.models.Item;
import machinecoding.auctionsystem.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BidTracker {

  void recordBidOnItem(Bid bid, Item item) throws InvalidBidException;

  Optional<Bid> getWinningBidForItem(Item item);

  List<Bid> getBidsForItem(Item item);

  Set<Item> getItemsWithBidFromUser(User user);

}