package ru.job4j.ood.isp.menu;
import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
       if (parentName == ROOT) {
           rootElements.add(new SimpleMenuItem(childName, actionDelegate));
           return true;
       }
       Optional<ItemInfo> op = findItem(parentName);
       if (op.isEmpty()) {
           return false;
       }
       op.get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
       return  true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> op = findItem(itemName);
        return op.map(itemInfo -> new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> it = new DFSIterator();

        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            @Override
            public MenuItemInfo next() {
                var itemInfo = it.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Iterator<ItemInfo> it = new DFSIterator();
        while (it.hasNext()) {
            ItemInfo item = it.next();
            if (item.menuItem.getName().equals(name)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
