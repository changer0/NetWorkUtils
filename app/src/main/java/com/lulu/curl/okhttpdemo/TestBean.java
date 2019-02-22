package com.lulu.curl.okhttpdemo;

import java.util.List;

/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public class TestBean {

    /**
     * Recommend : [{"id":"721223","name":"三国演义","qurl":"uniteqqreader://nativepage/book/detail?bid=721223","type":0},{"id":"13100","name":"小说","qurl":"uniteqqreader://nativepage/category/list?actionId=13100&actionTag=,-1,-1,-1,-1,6&pagestamp=1","type":3}]
     * ads : null
     * author : {"id":"3004580500820401","name":"木子喵喵.CS","qurl":"uniteqqreader://nativepage/authors/mainpage?authorId=3004580500820401","type":1}
     * books : [{"id":"164524","name":"微微一笑很倾城","qurl":"uniteqqreader://nativepage/book/detail?bid=164524","type":0},{"id":"449752","name":"解忧杂货店","qurl":"uniteqqreader://nativepage/book/detail?bid=449752","type":0},{"id":"852110","name":"浪花一朵朵（谭松韵、熊梓淇主演）","qurl":"uniteqqreader://nativepage/book/detail?bid=852110","type":0}]
     * category : {"id":"14300","name":"青春文学","qurl":"uniteqqreader://nativepage/category/list?actionId=14300&actionTag=,-1,-1,-1,-1,6&pagestamp=1","type":3}
     * tag : null
     */

    private AuthorBean author;
    private CategoryBean category;
    private List<RecommendBean> Recommend;
    private List<BooksBean> books;

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public List<RecommendBean> getRecommend() {
        return Recommend;
    }

    public void setRecommend(List<RecommendBean> Recommend) {
        this.Recommend = Recommend;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class AuthorBean {
        /**
         * id : 3004580500820401
         * name : 木子喵喵.CS
         * qurl : uniteqqreader://nativepage/authors/mainpage?authorId=3004580500820401
         * type : 1
         */

        private String id;
        private String name;
        private String qurl;
        private int type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQurl() {
            return qurl;
        }

        public void setQurl(String qurl) {
            this.qurl = qurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class CategoryBean {
        /**
         * id : 14300
         * name : 青春文学
         * qurl : uniteqqreader://nativepage/category/list?actionId=14300&actionTag=,-1,-1,-1,-1,6&pagestamp=1
         * type : 3
         */

        private String id;
        private String name;
        private String qurl;
        private int type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQurl() {
            return qurl;
        }

        public void setQurl(String qurl) {
            this.qurl = qurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class RecommendBean {
        /**
         * id : 721223
         * name : 三国演义
         * qurl : uniteqqreader://nativepage/book/detail?bid=721223
         * type : 0
         */

        private String id;
        private String name;
        private String qurl;
        private int type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQurl() {
            return qurl;
        }

        public void setQurl(String qurl) {
            this.qurl = qurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class BooksBean {
        /**
         * id : 164524
         * name : 微微一笑很倾城
         * qurl : uniteqqreader://nativepage/book/detail?bid=164524
         * type : 0
         */

        private String id;
        private String name;
        private String qurl;
        private int type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQurl() {
            return qurl;
        }

        public void setQurl(String qurl) {
            this.qurl = qurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "BooksBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", qurl='" + qurl + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}
