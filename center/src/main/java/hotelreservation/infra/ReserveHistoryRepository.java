package hotelreservation.infra;

import hotelreservation.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "reserveHistories",
    path = "reserveHistories"
)
public interface ReserveHistoryRepository
    extends PagingAndSortingRepository<ReserveHistory, Long> {}
