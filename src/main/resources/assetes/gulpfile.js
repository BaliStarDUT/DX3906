var gulp = require("gulp")
//var jshint = require("gulp-jshint")
//var templateCache = require("gulp-angular-templatecache")
//var concat = require("gulp-concat")

var paths = {
    "lib_dist"   : "../public/static",
    "app_dist":"../public/app",
}

gulp.task("gentelella",function(){
    gulp.src([
        './bower_components/gentelella/vendors/bootstrap/dist/**',
    ]).pipe(gulp.dest("../public/static/gentelella/bootstrap"));
    gulp.src([
        './bower_components/gentelella/vendors/font-awesome/**',
        '!./bower_components/gentelella/vendors/font-awesome/less/**',
        '!./bower_components/gentelella/vendors/font-awesome/scss/**',
    ]).pipe(gulp.dest("../public/static/gentelella/font-awesome"));
    gulp.src([
        './bower_components/gentelella/vendors/nprogress/**',
    ]).pipe(gulp.dest("../public/static/gentelella/nprogress"));
    gulp.src([
        './bower_components/gentelella/vendors/jquery/dist/**',
    ]).pipe(gulp.dest("../public/static/gentelella/jquery"));
    gulp.src([
        './bower_components/gentelella/vendors/fastclick/lib/**',
    ]).pipe(gulp.dest("../public/static/gentelella/fastclick"));
    gulp.src([
            './bower_components/gentelella/vendors/Chart.js/dist/**',
        ]).pipe(gulp.dest("../public/static/gentelella/Chart.js"));
    gulp.src([
            './bower_components/gentelella/vendors/skycons/**',
        ]).pipe(gulp.dest("../public/static/gentelella/skycons"));
    gulp.src([
            './bower_components/gentelella/build/**',
        ]).pipe(gulp.dest("../public/static/gentelella/custom"));
});

//gulp.task("init",function(){
//  gulp.src('../assets/js/custom/**/*.js')
//      .pipe(jshint())
//      .pipe(jshint.reporter('default'));
//});
//gulp.task("html2js",function(){
//  return gulp.src("../../../portainer/app/**/*.html")
//             .pipe(templateCache())
//             .pipe(gulp.dest("./public/template"));
//});

//gulp.task("concatjs",function(){
//  return gulp.src(["../../../portainer/app/**/*.js","../../../portainer/app/**/__module.js","!../../../portainer/app/**/*.spec.js","public/**/*.js"])
//             .pipe(concat('app.js'))
//             .pipe(gulp.dest("./public/js/"));
//});

//gulp.task("concatjslib",function(){
//  return gulp.src(["./node_modules/glob/common.js"])
//             .pipe(concat('angular.js'))
//             .pipe(gulp.dest("./public/"));
//});
gulp.task("vendor",function(){
//    gulp.src([
//        './bower_components/bootstrap/dist/css/bootstrap.min.css',
//        './bower_components/bootstrap/dist/js/bootstrap.min.js',
//        './bower_components/angular/angular.min.js',
//        './bower_components/angular-animate/angular-animate.min.js',
//        './bower_components/angular-touch/angular-touch.min.js',
//        './bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js',
//     ]).pipe(gulp.dest(paths.lib_dist));
    gulp.src([
             './bower_components/bootstrap/dist/**',
         ]).pipe(gulp.dest("../public/static/bootstrap"));
    gulp.src([
             './bower_components/tether/dist/**',
         ]).pipe(gulp.dest("../public/static/tether"));
    gulp.src([
            './bower_components/angular/*.js',
        ]).pipe(gulp.dest("../public/static/angular"));
    gulp.src([
            './bower_components/jquery/dist/**',
        ]).pipe(gulp.dest("../public/static/jquery"));
    gulp.src([
            './bower_components/angular-bootstrap/**',
        ]).pipe(gulp.dest("../public/static/angular-bootstrap"));
    gulp.src([
            './bower_components/angular-animate/**',
        ]).pipe(gulp.dest("../public/static/angular-animate"));
    gulp.src([
            './bower_components/angular-ui-router/release/**',
        ]).pipe(gulp.dest("../public/static/angular-ui-router"));

});