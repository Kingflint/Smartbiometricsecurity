package com.flint.smartsecurity;

public class AccessRecord {
    private String userId;
    private long timestamp;
    private long duration;

    public AccessRecord(String userId, long timestamp, long duration) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.duration = duration;
    }

    public String getUserId() { return userId; }
    public long getTimestamp() { return timestamp; }
    public long getDuration() { return duration; }
}
