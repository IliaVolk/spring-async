/**
 * Created by user on 06.06.2016.
 */


(function(){

    var app = angular.module("main", []);

    app.controller("MainController", ["$http", function($http){
        var self = this;

        self.messages = ["first"];

        self.anotherRequestAnswer = "";

        self.doRequest = function(){
            $http.get("messages").success(function(data){
                self.messages = data;
            });
        };
        self.doAnotherRequest = function(){
            $http.get("messages/another").success(function(data){
                self.anotherRequestAnswer = data;
            })
        };
    }])

})();
