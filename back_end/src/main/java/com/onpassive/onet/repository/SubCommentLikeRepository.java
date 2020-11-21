package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.onpassive.onet.entity.SubCommentLike;

@Repository
public interface SubCommentLikeRepository extends CrudRepository<SubCommentLike, Integer> {
	
	@Query( value = "SELECT count(*) FROM sub_comment_like where sub_comment_id=?1 and is_liked=1" , nativeQuery = true)
	public long likesCountOfSubCommentId(Integer commentId);
	
	
	SubCommentLike findByempIdAndSubCommentId(String empId, Integer subCommentId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update sub_comment_like set is_liked=:status where sub_comment_id=:subCommentId and emp_id=:empId", nativeQuery = true)
	int updateIsLikedByEmployeeID(Integer empId, Integer subCommentId,boolean status);
	

}
