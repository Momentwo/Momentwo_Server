package cord.eoeo.momentwo.global.application.port.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

// 확장용 인터페이스로 쓰기위해 제네릭 타입일 떈 빈으로 등록되지 않도록 설정하기
@NoRepositoryBean
public interface GenericDefaultJpaRepo<T, V> extends JpaRepository<T, V> {
}
