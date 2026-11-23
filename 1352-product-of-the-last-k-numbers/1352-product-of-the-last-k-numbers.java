import java.util.*;

class ProductOfNumbers {
    private List<Long> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1L);
    }

    public void add(int num) {
        if (num == 0) {
            prefix.clear();
            prefix.add(1L);
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();

        if (k >= n) {
            return 0;
        }

        return (int)(prefix.get(n - 1) / prefix.get(n - 1 - k));
    }
}