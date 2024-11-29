package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement// Ye sare trnsaction method ko eksath rakhega and then execute karayega. If poora succefully hojatahai run to commit kardega else rollback kardega.
public class JournalApplication {

    public static void main(String[] args) {

        SpringApplication.run(JournalApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory dbFactory){//platformTransaction ke andar apna vohi isko implement karaha hai MongodbTransaction, jiske anadar function hai
        return new MongoTransactionManager(dbFactory);//MongoDatabaseFactory mein dataBase store karlenege.
    }

}