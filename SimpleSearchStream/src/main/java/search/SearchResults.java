package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of how many times a word appears in an input string.
 */
public class SearchResults {
    /**
     * Hold the index of one search result.
     */
    public static class Result {
        /**
         * The index in the search String where the word that was
         * found.
         */
        private int mIndex;

        /**
         * Create a Result object contains meta-data about a search
         * result.
         */
        public Result(int index) {
            mIndex = index;
        }

        /**
         * Return the index.
         */
        public int getIndex() {
            return mIndex;
        }
    }

    /**
     * Id of the Thread that found a search result.
     */
    private long mThreadId;

    /**
     * The word that was found.
     */
    private String mWord;

    /**
     * The section title this search is associated with.
     */
    private String mTitle;

    /**
     * The cycle in which the search result was found.
     */
    private long mCycle;

    /**
     * The List of Result objects that matched the @code mWord.
     */
    private List<Result> mList;

    /**
     * Create an empty SearchResults, which is used to shutdown
     * processing of the BlockingQueue.
     */
    public SearchResults() {
        mList = null;
    }

    /**
     * Return the list of Results.
     */
    public List<Result> getResultList() {
        return mList;
    }

    /**
     * Create a SearchResults with values for the various fields.
     */
    public SearchResults(long threadId,
                         long cycle,
                         String word,
                         String title) {
        mThreadId = threadId;
        mCycle = cycle;
        mWord = word;
        mTitle = title;
        mList = new ArrayList<>();
    }

    /**
     * Create a SearchResults with values for the various fields.
     * This constructor is also passed a filled in resultList.
     */
    public SearchResults(long threadId,
                         long cycle,
                         String word,
                         String title,
                         List<Result> resultList) {
        mThreadId = threadId;
        mCycle = cycle;
        mWord = word;
        mTitle = title;
        mList = resultList;
    }

    public String getTitle() {
        return mTitle;
    }

    /**
     * Convert to header to String form.
     */
    public String headerToString() {
        return "";
            // "["
            // + mThreadId
            // + "|"
            // + mCycle
            // + "] "
            // + mTitle
            // "  \""
            // + mWord
            // + "\" at ";
    }

    /**
     * Add a Result.
     */
    public void add(int index) {
        mList.add(new Result(index));
    }

    /**
     * Returns true if there are no search results.
     */
    public boolean isEmpty() {
        return mList.size() == 0;
    }

    /**
     * Returns the number of results.
     */
    public int size() {
        return mList.size();
    }

    /**
     * Return the word.
     */
    public String getWord() {
        return mWord;
    }

    /**
     * Return a string version of the object.
     */
    @Override
    public String toString() {
        String output = new String("");

        if (!isEmpty()) {
            output += headerToString();

            // Iterate through the list of indices that matched the
            // search word and print them out.
            for (Result result : mList)
                output += 
                    "["
                    + result.mIndex
                    + "]";
        }
        
        return output;
    }

    /**
     * Print the results.
     */
    public SearchResults print() {
        if (!isEmpty()) {
            synchronized(System.out) {
                System.out.println(toString());
            }
        }
        return this;
    }
}

