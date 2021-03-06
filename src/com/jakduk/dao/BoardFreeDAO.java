package com.jakduk.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.jakduk.common.CommonConst;

/**
 * @author <a href="mailto:phjang1983@daum.net">Jang,Pyohwan</a>
 * @company  : http://jakduk.com
 * @date     : 2014. 12. 30.
 * @desc     :
 */

@Repository
public class BoardFreeDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public HashMap<String, Integer> getBoardFreeCommentCount(List<Integer> arrSeq) {
		
		AggregationOperation match = Aggregation.match(Criteria.where("boardItem.seq").in(arrSeq));
		AggregationOperation group = Aggregation.group("boardItem").count().as("count");
		//AggregationOperation sort = Aggregation.sort(Direction.ASC, "_id");
		//AggregationOperation limit = Aggregation.limit(CommonConst.BOARD_LINE_NUMBER);
		Aggregation aggregation = Aggregation.newAggregation(match, group/*, sort, limit*/);
		AggregationResults<BoardFreeCount> results = mongoTemplate.aggregate(aggregation, "boardFreeComment", BoardFreeCount.class);
		
		List<BoardFreeCount> boardCommentCount = results.getMappedResults();

		HashMap<String, Integer> commentCount = new HashMap<String, Integer>();
		
		for (BoardFreeCount item : boardCommentCount) {
			commentCount.put(item.getId(), item.getCount());
		}
		
		return commentCount;
	}
	
	public HashMap<String, Integer> getBoardFreeUsersLikingCount(List<Integer> arrSeq) {
		
		AggregationOperation unwind = Aggregation.unwind("usersLiking");
		AggregationOperation match = Aggregation.match(Criteria.where("seq").in(arrSeq));
		AggregationOperation group = Aggregation.group("_id").count().as("count");
		//AggregationOperation sort = Aggregation.sort(Direction.ASC, "_id");
		//AggregationOperation limit = Aggregation.limit(CommonConst.BOARD_LINE_NUMBER);
		Aggregation aggregation = Aggregation.newAggregation(unwind, match, group);
		AggregationResults<BoardFreeCount> results = mongoTemplate.aggregate(aggregation, "boardFree", BoardFreeCount.class);
		
		List<BoardFreeCount> boardCommentCount = results.getMappedResults();

		HashMap<String, Integer> commentCount = new HashMap<String, Integer>();
		
		for (BoardFreeCount item : boardCommentCount) {
			commentCount.put(item.getId(), item.getCount());
		}
		
		return commentCount;
	}
	
	public HashMap<String, Integer> getBoardFreeUsersDislikingCount(List<Integer> arrSeq) {
		
		AggregationOperation unwind = Aggregation.unwind("usersDisliking");
		AggregationOperation match = Aggregation.match(Criteria.where("seq").in(arrSeq));
		AggregationOperation group = Aggregation.group("_id").count().as("count");
		//AggregationOperation sort = Aggregation.sort(Direction.ASC, "_id");
		//AggregationOperation limit = Aggregation.limit(CommonConst.BOARD_LINE_NUMBER);
		Aggregation aggregation = Aggregation.newAggregation(unwind, match, group);
		AggregationResults<BoardFreeCount> results = mongoTemplate.aggregate(aggregation, "boardFree", BoardFreeCount.class);
		
		List<BoardFreeCount> boardCommentCount = results.getMappedResults();

		HashMap<String, Integer> commentCount = new HashMap<String, Integer>();
		
		for (BoardFreeCount item : boardCommentCount) {
			commentCount.put(item.getId(), item.getCount());
		}
		
		return commentCount;
	}
	
}
