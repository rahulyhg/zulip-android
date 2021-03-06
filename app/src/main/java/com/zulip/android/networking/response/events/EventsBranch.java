package com.zulip.android.networking.response.events;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;


public class EventsBranch {
    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public enum BranchType {
        MESSAGE(MessageWrapper.class, "message"),
        PRESENCE(PresenceWrapper.class, "presence"),
        SUBSCRIPTIONS(SubscriptionWrapper.class, "subscription"),
        MUTED_TOPICS(MutedTopicsWrapper.class, "muted_topics"),
        EDIT_MESSAGE_TIME_LIMIT(EditMessageWrapper.class, "realm"),
        UPDATE_MESSAGE(UpdateMessageWrapper.class, "update_message"),
        STREAM(StreamWrapper.class, "stream"),
        STAR_MESSAGE(StarWrapper.class, "update_message_flags"),
        REACTION(ReactionWrapper.class, "reaction");

        private final Class<? extends EventsBranch> klazz;
        private final String key;

        BranchType(@NonNull Class<? extends EventsBranch> klazz, @NonNull String serializeKey) {
            this.klazz = klazz;
            this.key = serializeKey;
        }

        @Nullable
        public static Class<? extends EventsBranch> fromRawType(@NonNull EventsBranch branch) {
            for (BranchType t : values()) {
                if (t.key.equalsIgnoreCase(branch.getType())) {
                    return t.klazz;
                }
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public Class<?> getKlazz() {
            return klazz;
        }
    }
}
