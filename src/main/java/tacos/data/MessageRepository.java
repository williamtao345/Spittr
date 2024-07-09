package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByUsername(String username);
}
