package wxx.java.appraise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.NewsService;

@RestController
@RequestMapping("news")
public class NewsController {
  private NewsService newsService;
    @Autowired
  public void setNewsService(NewsService newsService){
    this.newsService = newsService;
    }

    @RequestMapping("count")
  public Result count(@RequestHeader Integer id){
    return newsService.newsCount(id);
    }

  @RequestMapping("query")
  public Result query(@RequestHeader Integer id){
    return newsService.query(id);
  }
}
