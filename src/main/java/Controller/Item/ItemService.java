package Controller.Item;

import Model.DTO.ItemInfoDTO;
import javafx.collections.ObservableList;

public interface ItemService {

    void addItemInfo(String itemCode,String description,String category,int qtyOnHand,double unitPrice);

    void updateItemInfo(String itemCode,String description,String category,int qtyOnHand,double unitPrice);

    void deleteItemInfo(String itemCode);

    ObservableList<ItemInfoDTO> loadItemTable();
}
