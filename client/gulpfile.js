var gulp = require('gulp'),
  connect = require('gulp-connect');

gulp.task('connect', function() {
  connect.server({
    root: './',
    port: 8091,
    livereload: true
  });
});

gulp.task('html', function () {
  gulp.src('./src/**/*.js')
    .pipe(connect.reload());
});

gulp.task('watch', function () {
  gulp.watch(['./src/**/*.js'], ['html']);
});

gulp.task('default', ['connect', 'watch']);
