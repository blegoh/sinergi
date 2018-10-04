package onestep.id.sinergiin.Model;

/**
 * Created by eldi on 13/06/2018.
 */

public class BaseApi {
    /**
     *
     * http://192.168.43.241 wifi
     *
     * http://192.168.137.1 LAN
     *
     * http://209.97.170.199:8000/api/sinergiin/ => sofyan
     */



    public static final String BaseURL = "http://192.168.43.241/sinergiin/";
    public static final String loginURL=BaseURL+"user.php?fun=login";
    public static final String registerURL=BaseURL+"user.php?fun=register";

    public static final String insertProductURL=BaseURL+"product.php?fun=insert_product";
    public static final String updateProductURL=BaseURL+"shop.php?apicall=update_product";
    public static final String deleteProductURL=BaseURL+"shop.php?apicall=delete_product";

    public static final String getAllProductPembeliURL=BaseURL+"product.php?fun=get_all_product_pembeli";
    public static final String getAllProductPengrajinURL=BaseURL+"product.php?fun=get_all_product_pengrajin";
    public static final String getAllProductAdminURL=BaseURL+"product.php?fun=get_all_product_admin";
    public static final String ubahStatusProductAdminURL=BaseURL+"product.php?fun=ubah_status_product";


    public static final String insertBeli=BaseURL+"transaction.php?fun=insert_cart_pembelian";
    public static final String showCart=BaseURL+"transaction.php?fun=show_cart";


    public static final String imageURL=BaseURL+"gambar/";

    public static final String buyProduct = BaseURL + "shop.php?apicall=buy_product";
    public static final String getAllOrderByID = BaseURL + "shop.php?apicall=get_all_order_by_id";
    public static final String getAllBuyTransac = BaseURL + "shop.php?apicall=get_all_buy_transac_by_id";
}
