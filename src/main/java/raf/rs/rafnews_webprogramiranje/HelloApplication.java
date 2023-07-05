package raf.rs.rafnews_webprogramiranje;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import raf.rs.rafnews_webprogramiranje.repositories.category.CategoryRepository;
import raf.rs.rafnews_webprogramiranje.repositories.category.MySQLCategotyRepository;
import raf.rs.rafnews_webprogramiranje.repositories.comment.CommentRepository;
import raf.rs.rafnews_webprogramiranje.repositories.comment.MySQLCommentRepository;
import raf.rs.rafnews_webprogramiranje.repositories.news.MySQLNewsRepository;
import raf.rs.rafnews_webprogramiranje.repositories.news.NewsRepository;
import raf.rs.rafnews_webprogramiranje.repositories.newstag.MySQLNewsTagsRepository;
import raf.rs.rafnews_webprogramiranje.repositories.newstag.NewsTagsRepository;
import raf.rs.rafnews_webprogramiranje.repositories.tag.MySQLTagRepository;
import raf.rs.rafnews_webprogramiranje.repositories.tag.TagRepository;
import raf.rs.rafnews_webprogramiranje.repositories.user.MySQLUserRepository;
import raf.rs.rafnews_webprogramiranje.repositories.user.UserRepository;
import raf.rs.rafnews_webprogramiranje.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication(){
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                //System.out.println("APP CONFIGURED");
                this.bind(MySQLCategotyRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySQLCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySQLNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySQLNewsTagsRepository.class).to(NewsTagsRepository.class).in(Singleton.class);
                this.bind(MySQLTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySQLUserRepository.class).to(UserRepository.class).in(Singleton.class);

                this.bindAsContract(CategoryService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(NewsTagService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(UserService.class);
            }
        };

        register(binder);
        packages("raf.rs.rafnews_webprogramiranje");

        System.out.println("API");

    }

}