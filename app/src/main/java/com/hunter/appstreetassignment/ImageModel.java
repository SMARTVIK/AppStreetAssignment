package com.hunter.appstreetassignment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ImageModel {
    /**
     * totalHits : 500
     * hits : [{"largeImageURL":"https://pixabay.com/get/ea34b00c2bf5093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":671,"imageWidth":2736,"id":3113318,"user_id":7410713,"views":276807,"comments":112,"pageURL":"https://pixabay.com/en/sunflower-nature-flora-flower-3113318/","imageHeight":1824,"webformatURL":"https://pixabay.com/get/ea34b00c2bf5093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"sunflower, nature, flora","downloads":181962,"user":"bichnguyenvo","favorites":428,"imageSize":1026006,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2017/12/16/10-28-39-199_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/01/28/11/24/sunflower-3113318_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea35b70c2afc053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":791,"imageWidth":6000,"id":3063284,"user_id":1564471,"views":393952,"comments":179,"pageURL":"https://pixabay.com/en/rose-flower-petal-floral-noble-3063284/","imageHeight":4000,"webformatURL":"https://pixabay.com/get/ea35b70c2afc053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"rose, flower, petal","downloads":218235,"user":"annca","favorites":666,"imageSize":3574625,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea33b50f21f7043ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":148,"imageWidth":5574,"id":3640935,"user_id":334088,"views":31329,"comments":47,"pageURL":"https://pixabay.com/en/sunflowers-field-woman-yellow-3640935/","imageHeight":3717,"webformatURL":"https://pixabay.com/get/ea33b50f21f7043ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"sunflowers, field, woman","downloads":21072,"user":"jill111","favorites":107,"imageSize":4970597,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/08/29/22/52/sunflowers-3640935_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea37b00a29fc093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":358,"webformatWidth":640,"likes":326,"imageWidth":2027,"id":3215188,"user_id":7097598,"views":127552,"comments":94,"pageURL":"https://pixabay.com/en/flowers-orange-orange-petals-3215188/","imageHeight":1134,"webformatURL":"https://pixabay.com/get/ea37b00a29fc093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":83,"tags":"flowers, orange, orange petals","downloads":86775,"user":"Candiix","favorites":264,"imageSize":399066,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2017/12/06/20-08-45-84_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/03/10/20/26/flowers-3215188_150.jpg"},{"largeImageURL":"https://pixabay.com/get/eb37b80a2cf7053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":423,"imageWidth":5363,"id":2295434,"user_id":334088,"views":37143,"comments":30,"pageURL":"https://pixabay.com/en/spring-bird-bird-tit-spring-blue-2295434/","imageHeight":3575,"webformatURL":"https://pixabay.com/get/eb37b80a2cf7053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"spring bird, bird, tit","downloads":14095,"user":"jill111","favorites":472,"imageSize":2938651,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2017/05/08/13/15/spring-bird-2295434_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ee34b40a2cf41c22d2524518b74d4697ea72ebd011ac104496f7c470a4e4bdbc_1280.jpg","webformatHeight":360,"webformatWidth":640,"likes":240,"imageWidth":3020,"id":715540,"user_id":916237,"views":74529,"comments":28,"pageURL":"https://pixabay.com/en/yellow-natural-flower-blossom-715540/","imageHeight":1703,"webformatURL":"https://pixabay.com/get/ee34b40a2cf41c22d2524518b74d4697ea72ebd011ac104496f7c470a4e4bdbc_640.jpg","type":"photo","previewHeight":84,"tags":"yellow, natural, flower","downloads":33966,"user":"Wow_Pho","favorites":239,"imageSize":974940,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2015/04/07/14-10-15-590_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2015/04/10/00/41/yellow-715540_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea37b80d21f7033ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":400,"webformatWidth":640,"likes":328,"imageWidth":3200,"id":3292932,"user_id":2216431,"views":106295,"comments":33,"pageURL":"https://pixabay.com/en/sunflower-vase-vintage-retro-wall-3292932/","imageHeight":2000,"webformatURL":"https://pixabay.com/get/ea37b80d21f7033ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":93,"tags":"sunflower, vase, vintage","downloads":79459,"user":"Yuri_B","favorites":463,"imageSize":2563708,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/01/15/10-52-15-382_250x250.png","previewURL":"https://cdn.pixabay.com/photo/2018/04/05/14/09/sun-flower-3292932_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea33b50f21f7093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":55,"imageWidth":5760,"id":3640938,"user_id":334088,"views":16744,"comments":15,"pageURL":"https://pixabay.com/en/sunflowers-field-woman-yellow-3640938/","imageHeight":3840,"webformatURL":"https://pixabay.com/get/ea33b50f21f7093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"sunflowers, field, woman","downloads":14611,"user":"jill111","favorites":55,"imageSize":5058319,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/08/29/22/52/sunflowers-3640938_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea33b00b2ff6093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":72,"imageWidth":6000,"id":3614728,"user_id":1767157,"views":29373,"comments":46,"pageURL":"https://pixabay.com/en/sunflower-flower-petals-bloom-3614728/","imageHeight":4000,"webformatURL":"https://pixabay.com/get/ea33b00b2ff6093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"sunflower, flower, petals","downloads":26501,"user":"Capri23auto","favorites":48,"imageSize":4297956,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/05/16/20-25-22-995_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/08/18/13/00/sunflower-3614728_150.jpg"},{"largeImageURL":"https://pixabay.com/get/e830b20928fc093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":419,"webformatWidth":640,"likes":223,"imageWidth":4896,"id":1536088,"user_id":1195798,"views":260312,"comments":53,"pageURL":"https://pixabay.com/en/sunflower-flower-bloom-yellow-1536088/","imageHeight":3208,"webformatURL":"https://pixabay.com/get/e830b20928fc093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":98,"tags":"sunflower, flower, bloom","downloads":35199,"user":"Couleur","favorites":182,"imageSize":5103399,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/07/15/21-35-04-454_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2016/07/23/00/12/sun-flower-1536088_150.jpg"},{"largeImageURL":"https://pixabay.com/get/eb34b50a2df7083ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":390,"webformatWidth":640,"likes":286,"imageWidth":4000,"id":2145539,"user_id":2364555,"views":29468,"comments":27,"pageURL":"https://pixabay.com/en/crocus-flower-wet-spring-2145539/","imageHeight":2443,"webformatURL":"https://pixabay.com/get/eb34b50a2df7083ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":91,"tags":"crocus, flower, wet","downloads":15608,"user":"pixel2013","favorites":268,"imageSize":823922,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/02/25/16-46-24-974_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2017/03/15/09/00/crocus-2145539_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ec33b50e2ce90021d85a5854e74c479fe67ee7dd04b0144292f5c87aaee5b1_1280.jpg","webformatHeight":428,"webformatWidth":640,"likes":402,"imageWidth":3872,"id":56414,"user_id":9003,"views":48935,"comments":71,"pageURL":"https://pixabay.com/en/anemone-flower-blossom-bloom-blue-56414/","imageHeight":2592,"webformatURL":"https://pixabay.com/get/ec33b50e2ce90021d85a5854e74c479fe67ee7dd04b0144292f5c87aaee5b1_640.jpg","type":"photo","previewHeight":100,"tags":"anemone, flower, blossom","downloads":14545,"user":"Albenheim","favorites":334,"imageSize":770723,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2012/09/08/21-14-56-990_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2012/09/08/21/51/anemone-56414_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ee37b80a29f11c22d2524518b74d4697ea72ebd011ac104496f7c470a4e4bdbc_1280.jpg","webformatHeight":416,"webformatWidth":640,"likes":246,"imageWidth":1980,"id":729515,"user_id":909086,"views":26606,"comments":16,"pageURL":"https://pixabay.com/en/flower-beautiful-bloom-blooming-729515/","imageHeight":1288,"webformatURL":"https://pixabay.com/get/ee37b80a29f11c22d2524518b74d4697ea72ebd011ac104496f7c470a4e4bdbc_640.jpg","type":"photo","previewHeight":97,"tags":"flower, beautiful, bloom","downloads":16201,"user":"Bess-Hamiti","favorites":324,"imageSize":370390,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2017/09/19/10-02-47-777_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2015/04/19/08/33/flower-729515_150.jpg"},{"largeImageURL":"https://pixabay.com/get/e830b00d20f5023ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":323,"webformatWidth":640,"likes":188,"imageWidth":3861,"id":1512813,"user_id":2364555,"views":53992,"comments":20,"pageURL":"https://pixabay.com/en/lily-flowers-early-flower-garden-yellow--1512813/","imageHeight":1952,"webformatURL":"https://pixabay.com/get/e830b00d20f5023ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":75,"tags":"lily flowers early flower garden yellow flora yellow flower spring summer flowers flowers flowers flowers flowers flower","downloads":26940,"user":"pixel2013","favorites":182,"imageSize":1037708,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/02/25/16-46-24-974_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2016/07/12/18/54/lily-1512813_150.jpg"},{"largeImageURL":"https://pixabay.com/get/e83cb60d2cf5003ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":372,"webformatWidth":640,"likes":232,"imageWidth":4288,"id":1972411,"user_id":1777190,"views":78061,"comments":26,"pageURL":"https://pixabay.com/en/drip-blossom-bloom-yellow-1972411/","imageHeight":2499,"webformatURL":"https://pixabay.com/get/e83cb60d2cf5003ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":87,"tags":"drip, blossom, bloom","downloads":66846,"user":"susannp4","favorites":234,"imageSize":1510459,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2015/12/16/17-56-55-832_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2017/01/11/17/27/drip-1972411_150.jpg"},{"largeImageURL":"https://pixabay.com/get/e834b2082bf11c22d2524518b74d4697ea72ebd011ac104496f7c470a4e4bdbc_1280.jpg","webformatHeight":355,"webformatWidth":640,"likes":155,"imageWidth":2410,"id":113735,"user_id":817,"views":85701,"comments":27,"pageURL":"https://pixabay.com/en/rose-flower-yellow-yellow-rose-113735/","imageHeight":1337,"webformatURL":"https://pixabay.com/get/e834b2082bf11c22d2524518b74d4697ea72ebd011ac104496f7c470a4e4bdbc_640.jpg","type":"photo","previewHeight":83,"tags":"rose, flower, yellow","downloads":9977,"user":"blizniak","favorites":145,"imageSize":299425,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2013/06/28/17-07-05-714_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2013/05/26/12/14/rose-113735_150.jpg"},{"largeImageURL":"https://pixabay.com/get/e834b30829f3053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":302,"imageWidth":4752,"id":1127174,"user_id":1445608,"views":81880,"comments":22,"pageURL":"https://pixabay.com/en/sunflower-summer-yellow-nature-1127174/","imageHeight":3168,"webformatURL":"https://pixabay.com/get/e834b30829f3053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"sunflower, summer, yellow","downloads":48060,"user":"mploscar","favorites":296,"imageSize":3922163,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2016/01/05/14-08-20-943_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2016/01/08/05/24/sunflower-1127174_150.jpg"},{"largeImageURL":"https://pixabay.com/get/e836b00629f5053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":231,"imageWidth":4272,"id":1319114,"user_id":485024,"views":80872,"comments":23,"pageURL":"https://pixabay.com/en/girl-flowers-yellow-beauty-nature-1319114/","imageHeight":2848,"webformatURL":"https://pixabay.com/get/e836b00629f5053ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"girl, flowers, yellow","downloads":36760,"user":"AdinaVoicu","favorites":251,"imageSize":3837334,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/04/10/07-07-35-568_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2016/04/09/23/10/girl-1319114_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea35b40f28f3073ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":426,"webformatWidth":640,"likes":270,"imageWidth":5472,"id":3050076,"user_id":6663646,"views":124590,"comments":27,"pageURL":"https://pixabay.com/en/portrait-girl-fog-flowers-beauty-3050076/","imageHeight":3648,"webformatURL":"https://pixabay.com/get/ea35b40f28f3073ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":99,"tags":"portrait, girl, fog","downloads":87602,"user":"ThuyHaBich","favorites":260,"imageSize":2914111,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2017/11/17/08-35-57-188_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2017/12/30/13/25/portrait-3050076_150.jpg"},{"largeImageURL":"https://pixabay.com/get/ea30b50f2af2073ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg","webformatHeight":437,"webformatWidth":640,"likes":142,"imageWidth":5500,"id":3540266,"user_id":2364555,"views":65906,"comments":33,"pageURL":"https://pixabay.com/en/sunflower-flower-blossom-bloom-3540266/","imageHeight":3760,"webformatURL":"https://pixabay.com/get/ea30b50f2af2073ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg","type":"photo","previewHeight":102,"tags":"sunflower, flower, blossom","downloads":60189,"user":"pixel2013","favorites":136,"imageSize":4179978,"previewWidth":150,"userImageURL":"https://cdn.pixabay.com/user/2018/02/25/16-46-24-974_250x250.jpg","previewURL":"https://cdn.pixabay.com/photo/2018/07/15/19/02/sun-flower-3540266_150.jpg"}]
     * total : 16265
     */

    private int totalHits;
    private int total;
    private ArrayList<HitsBean> hits;

    public static ImageModel objectFromData(String str) {

        return new Gson().fromJson(str, ImageModel.class);
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<HitsBean> getHits() {
        return hits;
    }

    public void setHits(ArrayList<HitsBean> hits) {
        this.hits = hits;
    }

    public static class HitsBean implements Parcelable{
        /**
         * largeImageURL : https://pixabay.com/get/ea34b00c2bf5093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_1280.jpg
         * webformatHeight : 426
         * webformatWidth : 640
         * likes : 671
         * imageWidth : 2736
         * id : 3113318
         * user_id : 7410713
         * views : 276807
         * comments : 112
         * pageURL : https://pixabay.com/en/sunflower-nature-flora-flower-3113318/
         * imageHeight : 1824
         * webformatURL : https://pixabay.com/get/ea34b00c2bf5093ed1584d05fb1d4796e27ee7dc1cb90c4090f3c67caeeebcb0dc_640.jpg
         * type : photo
         * previewHeight : 99
         * tags : sunflower, nature, flora
         * downloads : 181962
         * user : bichnguyenvo
         * favorites : 428
         * imageSize : 1026006
         * previewWidth : 150
         * userImageURL : https://cdn.pixabay.com/user/2017/12/16/10-28-39-199_250x250.jpg
         * previewURL : https://cdn.pixabay.com/photo/2018/01/28/11/24/sunflower-3113318_150.jpg
         */

        private String largeImageURL;
        private int webformatHeight;
        private int webformatWidth;
        private int likes;
        private int imageWidth;
        private int id;
        private int user_id;
        private int views;
        private int comments;
        private String pageURL;
        private int imageHeight;
        private String webformatURL;
        private String type;
        private int previewHeight;
        private String tags;
        private int downloads;
        private String user;
        private int favorites;
        private int imageSize;
        private int previewWidth;
        private String userImageURL;
        private String previewURL;

        protected HitsBean(Parcel in) {
            largeImageURL = in.readString();
            webformatHeight = in.readInt();
            webformatWidth = in.readInt();
            likes = in.readInt();
            imageWidth = in.readInt();
            id = in.readInt();
            user_id = in.readInt();
            views = in.readInt();
            comments = in.readInt();
            pageURL = in.readString();
            imageHeight = in.readInt();
            webformatURL = in.readString();
            type = in.readString();
            previewHeight = in.readInt();
            tags = in.readString();
            downloads = in.readInt();
            user = in.readString();
            favorites = in.readInt();
            imageSize = in.readInt();
            previewWidth = in.readInt();
            userImageURL = in.readString();
            previewURL = in.readString();
        }

        public static final Creator<HitsBean> CREATOR = new Creator<HitsBean>() {
            @Override
            public HitsBean createFromParcel(Parcel in) {
                return new HitsBean(in);
            }

            @Override
            public HitsBean[] newArray(int size) {
                return new HitsBean[size];
            }
        };

        public static HitsBean objectFromData(String str) {

            return new Gson().fromJson(str, HitsBean.class);
        }

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public int getWebformatHeight() {
            return webformatHeight;
        }

        public void setWebformatHeight(int webformatHeight) {
            this.webformatHeight = webformatHeight;
        }

        public int getWebformatWidth() {
            return webformatWidth;
        }

        public void setWebformatWidth(int webformatWidth) {
            this.webformatWidth = webformatWidth;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getPageURL() {
            return pageURL;
        }

        public void setPageURL(String pageURL) {
            this.pageURL = pageURL;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        public String getWebformatURL() {
            return webformatURL;
        }

        public void setWebformatURL(String webformatURL) {
            this.webformatURL = webformatURL;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPreviewHeight() {
            return previewHeight;
        }

        public void setPreviewHeight(int previewHeight) {
            this.previewHeight = previewHeight;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public int getFavorites() {
            return favorites;
        }

        public void setFavorites(int favorites) {
            this.favorites = favorites;
        }

        public int getImageSize() {
            return imageSize;
        }

        public void setImageSize(int imageSize) {
            this.imageSize = imageSize;
        }

        public int getPreviewWidth() {
            return previewWidth;
        }

        public void setPreviewWidth(int previewWidth) {
            this.previewWidth = previewWidth;
        }

        public String getUserImageURL() {
            return userImageURL;
        }

        public void setUserImageURL(String userImageURL) {
            this.userImageURL = userImageURL;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(largeImageURL);
            parcel.writeInt(webformatHeight);
            parcel.writeInt(webformatWidth);
            parcel.writeInt(likes);
            parcel.writeInt(imageWidth);
            parcel.writeInt(id);
            parcel.writeInt(user_id);
            parcel.writeInt(views);
            parcel.writeInt(comments);
            parcel.writeString(pageURL);
            parcel.writeInt(imageHeight);
            parcel.writeString(webformatURL);
            parcel.writeString(type);
            parcel.writeInt(previewHeight);
            parcel.writeString(tags);
            parcel.writeInt(downloads);
            parcel.writeString(user);
            parcel.writeInt(favorites);
            parcel.writeInt(imageSize);
            parcel.writeInt(previewWidth);
            parcel.writeString(userImageURL);
            parcel.writeString(previewURL);
        }
    }
/*
    private ArrayList<ImagesBean> images;

    public static ImageModel objectFromData(String str) {

        return new Gson().fromJson(str, ImageModel.class);
    }

    public ArrayList<ImagesBean> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean implements Parcelable {
        *//**
         * id : 414
         * url : https://splashbase.s3.amazonaws.com/unsplash/regular/tumblr_mtax0twHix1st5lhmo1_1280.jpg
         * large_url : https://splashbase.s3.amazonaws.com/unsplash/large/1bqbiVH
         * source_id : 64
         *//*

        private int id;
        private String url;
        private String large_url;
        private int source_id;

        protected ImagesBean(Parcel in) {
            id = in.readInt();
            url = in.readString();
            large_url = in.readString();
            source_id = in.readInt();
        }

        public static final Creator<ImagesBean> CREATOR = new Creator<ImagesBean>() {
            @Override
            public ImagesBean createFromParcel(Parcel in) {
                return new ImagesBean(in);
            }

            @Override
            public ImagesBean[] newArray(int size) {
                return new ImagesBean[size];
            }
        };

        public static ImagesBean objectFromData(String str) {

            return new Gson().fromJson(str, ImagesBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLarge_url() {
            return large_url;
        }

        public void setLarge_url(String large_url) {
            this.large_url = large_url;
        }

        public int getSource_id() {
            return source_id;
        }

        public void setSource_id(int source_id) {
            this.source_id = source_id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(url);
            parcel.writeString(large_url);
            parcel.writeInt(source_id);
        }
    }*/












}
