import java.util.Iterator;
import java.util.List;
import java.util.Stack;


interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> stack = new Stack<>();
    private Integer consumed = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        hasNext();
        Integer toReturn = consumed;
        consumed = null;
        return toReturn;
    }

    @Override
    public boolean hasNext() {
        if (consumed!=null) return true;
        while (!stack.isEmpty()) {
            if (consumed != null) return true;
            if (stack.peek().hasNext()) {
                NestedInteger i = stack.peek().next();
                if (i.isInteger()) {
                    consumed = i.getInteger();
                    return true;
                } else
                    stack.push(i.getList().iterator());
            } else
                stack.pop();
        }
        return false;
    }
}

///
//  Your NestedIterator object will be instantiated and called as such:
//  NestedIterator i = new NestedIterator(nestedList);
//  while (i.hasNext()) v[f()] = i.next();
// /