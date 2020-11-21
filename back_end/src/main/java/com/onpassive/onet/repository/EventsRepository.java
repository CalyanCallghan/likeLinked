package com.onpassive.onet.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.entity.Events;
import com.onpassive.onet.entity.User;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {


	public List<Events> findAll();

	@Query("SELECT u FROM User u where u.empId=?1")
	User findUserByEmpId(long empId);

	long countByStatus(String status);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update events e set e.status=?1 where e.event_id = ?2", nativeQuery = true)
	int updateAsUnread(String status, long noticationId);

	public Events findByeventId(long eventId);

	@Query(value = "SELECT * FROM events where DATE_FORMAT(event_start_date, '%Y-%m-%d') <= DATE_FORMAT(NOW(), '%Y-%m-%d')"
			+ "	and  DATE_FORMAT(event_end_date, '%Y-%m-%d') >= DATE_FORMAT(NOW(), '%Y-%m-%d')", nativeQuery = true)
	public List<Events> getLiveEvents();
	
	@Query(value = "SELECT * FROM events where DATE_FORMAT(event_start_date, '%Y-%m-%d') > DATE_FORMAT(NOW(), '%Y-%m-%d')"
			+ "	and  DATE_FORMAT(event_end_date, '%Y-%m-%d') >= DATE_FORMAT(NOW(), '%Y-%m-%d')", nativeQuery = true)
	public List<Events> getFutureEvents();
	
	@Query(value = "SELECT * FROM events where DATE_FORMAT(event_start_date, '%Y-%m-%d') < DATE_FORMAT(NOW(), '%Y-%m-%d')"
			+ "and  DATE_FORMAT(event_end_date, '%Y-%m-%d') < DATE_FORMAT(NOW(), '%Y-%m-%d')", nativeQuery = true)
	public List<Events> getArchiveEvent();
	
	@Query(value="SELECT * FROM events ORDER BY event_id DESC LIMIT 10",nativeQuery = true)
	public List<Events> displayTenEvents();
	


	

}
