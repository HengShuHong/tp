//@@author HengShuHong
package command;

import exceptions.EmptyListException;
import item.Item;
import itemlist.Itemlist;
import ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command {

    protected String keyword;

    protected String itemInfo;

    public FindCommand(String itemInfo, String keyword) {
        this.keyword = keyword.toLowerCase();
        this.itemInfo = itemInfo;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Searches for item(s) given a specified keyword
     *
     * @throws EmptyListException when the filtered list is empty (nothing found)
     */
    @Override
    public void execute() throws EmptyListException {
        if (itemInfo.equals("NA")) {
            itemInfo = "item + qty + uom + cat + buy + sell";
        }
        ArrayList<String> searchList = filterList();
        TextUi.showList(searchList);
        LOGGER.info("Itemlist successfully filtered.");
    }

    /**
     * Enhances the find feature by searching through a filtered list, e.g. search for "fruits" in /item /uom
     *
     * @return a list of items that matches the filter and the keyword
     * @throws EmptyListException when the filtered list is empty (nothing found)
     */
    public ArrayList<String> filterList() throws EmptyListException {
        ArrayList<String> searchList = new ArrayList<>();
        for (Item item : Itemlist.getItems()) {
            if (itemInfo.toLowerCase().contains("item") && item.getItemName().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("qty") && Integer.toString(item.getQuantity()).equals(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("uom") && item.getUnitOfMeasurement().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (item.getCategory() != null) {
                if (itemInfo.toLowerCase().contains("cat") && item.getCategory().toLowerCase().contains(keyword)) {
                    searchList.add(String.valueOf(item));
                }
            }
            if (itemInfo.toLowerCase().contains("buy") && Float.toString(item.getBuyPrice()).equals(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("sell") && Float.toString(item.getSellPrice()).equals(keyword)) {
                searchList.add(String.valueOf(item));
            }
        }
        if (searchList.isEmpty()) {
            LOGGER.warning("Item not found.");
            throw new EmptyListException("Item");
        }
        return searchList;
    }

}
