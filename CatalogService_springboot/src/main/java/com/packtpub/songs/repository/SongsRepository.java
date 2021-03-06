package com.packtpub.songs.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.packtpub.songs.model.Song;
import com.packtpub.songs.repository.dynamodb.DynamoDBSongItem;

public class SongsRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public SongsRepository(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Optional<Song> getSong(final String songIdentifier) {
        DynamoDBSongItem songItem = dynamoDBMapper.load(DynamoDBSongItem.class, songIdentifier);

        if (songItem == null) {
            return Optional.empty();
        }

        return Optional.of(songItem.toSong());
    }

    public void storeSong(final Song song) {
        final DynamoDBSongItem dynamoDBSongItem = DynamoDBSongItem.fromSong(song);
        final DynamoDBSaveExpression dynamoDBSaveExpression = getSongIdDoesNotExistExpression();

        try {
        	 System.out.println("<SongsRepository> Storing song item :" + song);
            dynamoDBMapper.save(dynamoDBSongItem, dynamoDBSaveExpression);
            
            System.out.println(" <SongsRepository> Song Item stored successfully !!");
        } catch (ConditionalCheckFailedException e) {
            throw new SongIdentifierExistsException(song.getId());
        }
    }

    private DynamoDBSaveExpression getSongIdDoesNotExistExpression() {
        DynamoDBSaveExpression samePartitionIdExistsExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expected = new HashMap<>();
        expected.put("id", new ExpectedAttributeValue(false));
        samePartitionIdExistsExpression.setExpected(expected);

        return samePartitionIdExistsExpression;
    }
}
