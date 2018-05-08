package cn.xdev.taobao.sdk;

import com.taobao.api.*;

public class TbkRequest {

    public static String itemFields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick";
    public static String itemInfoFields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,volume,cat_leaf_name,cat_name";
    public static String shopFields = "user_id,shop_title,shop_type,seller_nick,pict_url,shop_url";
    public static String eventFields = "event_id,event_title,start_time,end_time";
    public static String eventItemFields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,type,status";
    public static String favoritesFields = "favorites_title,favorites_id,type";
    public static String favoritesItemFields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type";
    public static String tqgFields = "click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time";


    public static TaobaoResponse doRequest(BaseTaobaoRequest req) {

        TbkApi api = new TbkApi();
        TaobaoClient client = new DefaultTaobaoClient(api.getApi_url(), api.getApp_key(), api.getApp_secret(), "json");
        TaobaoResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
//        System.out.println(rsp.getBody());
        return rsp;
    }

}
