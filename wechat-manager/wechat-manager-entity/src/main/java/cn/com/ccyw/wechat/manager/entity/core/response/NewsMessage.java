package cn.com.ccyw.wechat.manager.entity.core.response;

import java.util.List;

/**
 * @author luoc
 * @version V1.0
 * @package cn.com.ccyw.wechat.manager.entity.core.response
 * @description: TODO
 * @date 2017/12/24 19:03
 */
public class NewsMessage extends BaseMessage {
    //图文消息个数，限制为10条以内
    private int ArticleCount;
    //多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
