package com.example.demo.commonEnum;

public enum GenreMessage {

    EMPTY("1","ジャンル名を入力してください"),
    EXIST("2","既に存在するジャンルです"),
	SUCCEED("3","ジャンルを追加しました");

	private String id;
    private String message;


    private GenreMessage(String id,String message) {
    	this.id = id;
        this.message = message;
    }

    public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public static GenreMessage getById(String id) {

        for( GenreMessage flt : GenreMessage.values() ) { //拡張for文による走査
            if( flt.getId().equals(id) ) {
                return flt;                 //条件に一致するインスタンスを返す
            }
        }
        return null;
    }
}