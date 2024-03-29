var gulp = require('gulp'),
  connect = require('gulp-connect');

gulp.task('connect', function() {
  connect.server({
    root: './',
    port: 8091,
    fallback: 'src/test/results.html',
    livereload: true
  });
});

gulp.task('html', function () {
  gulp.src('./src/**/*.js')
    .pipe(connect.reload());
});

gulp.task('watch', function () {
  gulp.watch(['./src/**/*.js', './src/test/*.html'], ['html']);
});

gulp.task('default', ['connect', 'watch']);
