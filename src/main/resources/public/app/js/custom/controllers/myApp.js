var app = angular.module("myApp", ['ui.bootstrap','ui.router','infrastructure']);
//,'ui.router', 'infrastructure'
app.config(['$stateProvider','$urlRouterProvider','$locationProvider','$httpProvider',function($stateProvider,$urlRouterProvider,$locationProvider,$httpProvider){

    $httpProvider.defaults.headers.common["Cache-Control"] = 'no-cache';
    $httpProvider.interceptors.push('HttpErrorHandlingInterceptor');
    $locationProvider.hashPrefix('!')

    $urlRouterProvider.otherwise('/welcome');

    $stateProvider.state('app',{
      abstract: true,
      url: '',
      template: '<ui-view></ui-view>'
    }).state('welcome',{
      parent: 'app',
      url: '/welcome',
      template: '<layout-welcome>welcome</layout-welcome>'
    }).state('info',{
      parent: 'app',
      url: '/info',
      template: '<layout-info>this is info page</layout-info>'
    });
}]);

angular.module('layoutInfo',[]);
angular.module('layoutInfo').component('layoutInfo',{
  templateUrl: "app/html/widgets/weatherWidget.html"
});
