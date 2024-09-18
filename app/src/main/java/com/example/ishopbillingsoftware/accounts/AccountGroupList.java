package com.example.ishopbillingsoftware.accounts;

public class AccountGroupList {
    private String _id;
    private String groupAccountId;
    private String search;
    private String group;
    private String underGroup;
    private int __v;


    public AccountGroupList(String _id, String groupAccountId, String search, String group, String underGroup, int __v) {
        this._id = _id;
        this.groupAccountId = groupAccountId;
        this.search = search;
        this.group = group;
        this.underGroup = underGroup;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGroupAccountId() {
        return groupAccountId;
    }

    public void setGroupAccountId(String groupAccountId) {
        this.groupAccountId = groupAccountId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUnderGroup() {
        return underGroup;
    }

    public void setUnderGroup(String underGroup) {
        this.underGroup = underGroup;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
