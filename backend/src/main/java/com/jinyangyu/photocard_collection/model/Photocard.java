package com.jinyangyu.photocard_collection.model;

import java.util.ArrayList;

import org.json.JSONObject;

import com.jinyangyu.photocard_collection.persistence.Writable;

// Represents a K-pop photocard with details about the member, 
// the group they belong to, the photocard's trade status, 
// and whether the group is a girl group.
public class Photocard implements Writable {
    private String memberName;
    private String groupName;
    private boolean tradeStatus;
    private boolean girlGroup;

    // REQUIRES: the first letter of memberName must be capitalized;
    // the groupName must be entirely in uppercase, no space.
    // EFFECTS: constructs a photocard with the specified member name,
    // group name, and trade availability
    public Photocard(String memberName, String groupName, boolean tradeStatus) {
        this.memberName = memberName;
        this.groupName = groupName;
        this.tradeStatus = tradeStatus;
    }

    // REQUIRES: the first letter of memberName must be capitalized
    // MODIFIES: this
    // EFFECTS: set the member's name on this photocard
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    // REQUIRES: the groupName must be entirely in uppercase
    // MODIFIES: this
    // EFFECTS: set the group name for this photocard
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    // MODIFIES: this
    // EFFECTS: set the trade status of this photocard.
    // true if the photocard is available for trade, false otherwise
    public void setTradeStatus(boolean tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    // MODIFIES: this
    // EFFECTS: Mark this photocard as belonging to a girl group or a boy group.
    // true if the photocard belongs to a girl group, false otherwise
    public void setGirlGroup(boolean girlGroup) {
        this.girlGroup = girlGroup;
    }

    // MODIFIES: this
    // EFFECTS: Updates the girl group status of the photocard based on
    // whether the group's name exists in the predefined list of girl groups
    public void isGirlGroup(Photocard card) {
        ArrayList<String> girlGroups = new ArrayList<String>();
        girlGroups.add("TWICE");
        girlGroups.add("BLACKPINK");
        girlGroups.add("IVE");
        girlGroups.add("AESPA");
        girlGroups.add("NEWJEANS");
        girlGroups.add("LESSERAFIM");
        if (girlGroups.contains(card.getGroupName())) {
            card.setGirlGroup(true);
        } else {
            card.setGirlGroup(false);
        }
    }

    // EFFECTS: get the member's name of this photocard
    public String getMemberName() {
        return memberName;
    }

    // EFFECTS: get the name of the group of this photocard
    public String getGroupName() {
        return groupName;
    }

    // EFFECTS: get the trade status of this photocard.
    // return true if the photocard is available for trade, false otherwise
    public boolean getTradeStatus() {
        return tradeStatus;
    }

    // EFFECTS: check if this photocard belongs to a girl group.
    // return true if this is a girl group photocard, false otherwise
    public boolean getGirlGroup() {
        return girlGroup;
    }

    // EFFECTS: get the corresponding image path for the photocard
    public String getImagePath() {
        return "/images/" + groupName + "_" + memberName + ".jpg";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("memberName", memberName);
        json.put("groupName", groupName);
        json.put("tradeStatus", tradeStatus);
        json.put("imagePath",   getImagePath()); 
        return json;
    }
}
