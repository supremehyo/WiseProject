package com.example.wisesayproject.DTO

// 앞에 var 나 val 안붙이면 값 제대로 안만들어짐 생성자 생성때는 하나이상의 매개변수가 필요하며 var val 는 필수적으로 적어야한다.
class SaveWise(var writeusernickname : String , var content :String , var userNickname :String , var imagename:String , var  myquestion : String)