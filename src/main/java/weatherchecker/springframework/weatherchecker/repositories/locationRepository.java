package weatherchecker.springframework.weatherchecker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import weatherchecker.springframework.weatherchecker.models.location;

import java.util.List;

public interface locationRepository extends JpaRepository<location, Long> {

    List<location> findByusernameContaining(@Param("username") String username);
}
