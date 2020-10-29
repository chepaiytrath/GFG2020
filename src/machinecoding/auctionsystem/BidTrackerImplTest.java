package machinecoding.auctionsystem;

import machinecoding.auctionsystem.models.Item;
import machinecoding.auctionsystem.models.User;
import machinecoding.auctionsystem.service.BidTrackerImpl;
//import org.junit.jupiter.api.Test;

public class BidTrackerImplTest {

    private final User user1 = new User("u1", "Nicolas Bentayou");
    private final User user2 = new User("u2", "Randolph Carter");
    private final User user3 = new User("u3", "Herbert West");
    private final Item item1 = new Item("i1", "item1", "Brilliant!");
    private final Item item2 = new Item("i2", "item2", "Brilliant!");
    private final Item item3 = new Item("i3", "item3", "Brilliant!");

    private BidTrackerImpl bidTracker;

    /*@Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void initAuctionRoom() {
        bidTracker = new BidTrackerImpl();
    }

    @Test
    public void recordBidOnItem_shouldAddAFirstBidToItem_whenBidIsValid() throws InvalidBidException {
        Bid bid = new Bid(user1, 10);

        bidTracker.recordBidOnItem(bid, item1);

        List<Bid> actualBidListOnItem1 = bidTracker.getCurrentAuctionBoardCopy().get(item1);
        List<Bid> expectedBidListOnItem1 = Collections.singletonList(bid);
        assertEquals(expectedBidListOnItem1, actualBidListOnItem1);
    }

    @Test
    public void recordBidOnItem_shouldAddSeveralBidsToItem_whenBidsAreValid() throws InvalidBidException {
        bidTracker.recordBidOnItem(new Bid(user1, 10), item1);
        bidTracker.recordBidOnItem(new Bid(user2, 20), item1);
        bidTracker.recordBidOnItem(new Bid(user1, 30), item1);

        List<Bid> actualBidsListOnItem1 = bidTracker.getCurrentAuctionBoardCopy().get(item1);
        List<Bid> expectedBidsListOnItem1 = Arrays.asList(
                new Bid(user1, 10),
                new Bid(user2, 20),
                new Bid(user1, 30));
        assertEquals(expectedBidsListOnItem1, actualBidsListOnItem1);
    }

    @Test
    public void recordBidOnItem_shouldThrowIllegalArgumentException_whenItemIsNull() throws InvalidBidException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Item can't be null");

        bidTracker.recordBidOnItem(new Bid(user1, 10), null);
    }

    @Test
    public void recordBidOnItem_shouldThrowIllegalArgumentException_whenBidIsNull() throws InvalidBidException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bid can't be null");

        bidTracker.recordBidOnItem(null, item1);
    }

    @Test
    public void recordBidOnItem_shouldThrowIllegalArgumentException_whenUserIsNull() throws InvalidBidException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bid's user can't be null");

        bidTracker.recordBidOnItem(new Bid(null, 10), item1);
    }

    @Test
    public void recordBidOnItem_shouldThrowInvalidBidException_whenBidIsLowerThanCurrentlyWinningBid() throws InvalidBidException {
        thrown.expect(InvalidBidException.class);
        thrown.expectMessage("A bid of £5 on item { id: i1, name: item1, description: Brilliant! } is too low. It should be more than the current winning bid: £10)");

        bidTracker.recordBidOnItem(new Bid(user1, 10), item1);

        Bid lowBid = new Bid(user2, 5);
        bidTracker.recordBidOnItem(lowBid, item1);
    }

    @Test
    public void recordBidOnItem_shouldThrowInvalidBidException_whenBidIsSameAsCurrentlyWinningBid() throws InvalidBidException {
        thrown.expect(InvalidBidException.class);
        thrown.expectMessage("A bid of £10 on item { id: i1, name: item1, description: Brilliant! } is too low. It should be more than the current winning bid: £10)");

        bidTracker.recordBidOnItem(new Bid(user1, 10), item1);

        Bid sameBid = new Bid(user2, 10);
        bidTracker.recordBidOnItem(sameBid, item1);
    }

    @Test
    public void recordBidOnItem_shouldAddSeveralBidsToItem_whenSomeBidsAreInvalid() throws InvalidBidException {
        bidTracker.recordBidOnItem(new Bid(user1, 10), item1);
        bidTracker.recordBidOnItem(new Bid(user2, 20), item1);
        try { // invalid bid
            bidTracker.recordBidOnItem(new Bid(user3, 15), item1);
        } catch (InvalidBidException e) { *//* Silencing the exception as it is irrelevant for this test *//* }
        bidTracker.recordBidOnItem(new Bid(user1, 30), item1);

        List<Bid> bidsListOnItem1 = bidTracker.getCurrentAuctionBoardCopy().get(item1);

        List<Bid> expectedBidsOnItem1 = Arrays.asList(
                new Bid(user1, 10),
                new Bid(user2, 20),
                new Bid(user1, 30));
        assertEquals(expectedBidsOnItem1, bidsListOnItem1);
    }

    @Test
    public void recordBidOnItem_shouldOnlyRecordValidBids_inAMultithreadedEnvironment() {
        AtomicInteger invalidBidsCount = new AtomicInteger(0);

        // Make 10000 bids on 4 different threads.
        int totalNbBids = 10000;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        IntStream.range(0, totalNbBids).forEach(
                i -> executor.submit(
                        () -> {
                            try {
                                bidTracker.recordBidOnItem(new Bid(user1, i), item1);
                            } catch (InvalidBidException e) {
                                invalidBidsCount.incrementAndGet();
                            }
                        }
                )
        );
        shutDownExecutor(executor);

        List<Bid> actualBidsMade = bidTracker.getCurrentAuctionBoardCopy().get(item1);

        // asserting that all bids were processed
        assertEquals(totalNbBids, actualBidsMade.size() + invalidBidsCount.get());
        // asserting that the accepted bids for the item are all ordered by increasing value
        assertEquals(actualBidsMade, sortBidListByValue(actualBidsMade));
        // asserting that the last bid is for 9999
        Bid lastBidMade = actualBidsMade.get(actualBidsMade.size() - 1);
        assertEquals(totalNbBids - 1, lastBidMade.getValue());
    }

    private void shutDownExecutor(ExecutorService executor) {
        try {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            executor.shutdownNow();
        }
    }

    private List<Bid> sortBidListByValue(List<Bid> bidList) {
        return bidList.stream()
                .sorted(Comparator.comparing(Bid::getValue))
                .collect(Collectors.toList());
    }

    @Test
    public void getCurrentWinningBidForItem_shouldThrowIllegalArgumentException_whenItemIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Item can't be null");

        bidTracker.getCurrentWinningBidForItem(null);
    }

    @Test
    public void getCurrentWinningBidForItem_shouldReturnEmptyOptional_whenItemHasNoBid() {
        Optional<Bid> bid = bidTracker.getCurrentWinningBidForItem(item1);
        assertEquals(Optional.empty(), bid);
    }

    @Test
    public void getCurrentWinningBidForItem_shouldReturnOptionalWithAValue_whenItemHasBids() throws InvalidBidException {
        bidTracker.recordBidOnItem(new Bid(user1, 10), item1);
        bidTracker.recordBidOnItem(new Bid(user2, 20), item1);

        Optional<Bid> bid = bidTracker.getCurrentWinningBidForItem(item1);

        assertTrue(bid.isPresent());
        assertEquals(bid.get(), new Bid(user2, 20));
    }

    @Test
    public void getBidsForItem_shouldThrowIllegalArgumentException_whenItemIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Item can't be null");

        bidTracker.getBidsForItem(null);
    }

    @Test
    public void getBidsForItem_shouldReturnEmptyList_whenItemHasNoBid() {
        List<Bid> bids = bidTracker.getBidsForItem(item1);
        assertTrue(bids.isEmpty());
    }

    @Test
    public void getBidsForItem_shouldReturnTheCorrectListOfBids_whenItemHasBids() throws InvalidBidException {
        bidTracker.recordBidOnItem(new Bid(user1, 10), item1);
        bidTracker.recordBidOnItem(new Bid(user2, 20), item1);

        List<Bid> actualBids = bidTracker.getBidsForItem(item1);

        List<Bid> expectedBids = Arrays.asList(
                new Bid(user1, 10),
                new Bid(user2, 20));
        assertEquals(expectedBids, actualBids);
    }


    @Test
    public void getItemsWithBidFromUser_shouldReturnEmptySet_whenUserIsNull() {
        Set<Item> items = bidTracker.getItemsWithBidFromUser(null);
        assertTrue(items.isEmpty());
    }

    @Test
    public void getItemsWithBidFromUser_shouldReturnEmptySet_whenUserHasNoBid() {
        Set<Item> items = bidTracker.getItemsWithBidFromUser(user1);
        assertTrue(items.isEmpty());
    }

    @Test
    public void getItemsWithBidFromUser_shouldReturnCorrectItemSet_whenUserHasBids() throws InvalidBidException {
        bidTracker.recordBidOnItem(new Bid(user1, 10), item1); // bid on item1
        bidTracker.recordBidOnItem(new Bid(user2, 20), item1);
        bidTracker.recordBidOnItem(new Bid(user1, 30), item1); // second bid on item1
        bidTracker.recordBidOnItem(new Bid(user2, 10), item2);
        bidTracker.recordBidOnItem(new Bid(user3, 20), item2);
        bidTracker.recordBidOnItem(new Bid(user3, 10), item3);
        bidTracker.recordBidOnItem(new Bid(user1, 20), item3); // bid on item3

        Set<Item> itemList = bidTracker.getItemsWithBidFromUser(user1);

        Set<Item> expectedItemList = new HashSet<>(Arrays.asList(item1, item3));
        assertEquals(expectedItemList, itemList);
    }*/
}