package de.kopis.twittercleaner.util.cmd;

import java.util.Date;

import twitter4j.Annotations;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

@SuppressWarnings("serial")
public class DummyStatusImpl implements Status {
    private final long statusId;

    public DummyStatusImpl(long statusId) {
        this.statusId = statusId;
    }

    @Override
    public int compareTo(Status o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public RateLimitStatus getRateLimitStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getCreatedAt() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getId() {
        return statusId;
    }

    @Override
    public String getText() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSource() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isTruncated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long getInReplyToStatusId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getInReplyToUserId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getInReplyToScreenName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GeoLocation getGeoLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Place getPlace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFavorited() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isRetweet() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Status getRetweetedStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getContributors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getRetweetCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isRetweetedByMe() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public UserMentionEntity[] getUserMentionEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public URLEntity[] getURLEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HashtagEntity[] getHashtagEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Annotations getAnnotations() {
        // TODO Auto-generated method stub
        return null;
    }
}
