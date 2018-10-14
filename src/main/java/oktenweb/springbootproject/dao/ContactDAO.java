package oktenweb.springbootproject.dao;

import oktenweb.springbootproject.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactDAO extends JpaRepository<Contact, Integer> {

    @Query("select c from Contact c where c.name=:xxx")
    List<Contact> byName(@Param("xxx") String name);

    List<Contact> findAllByName(String name);


}
