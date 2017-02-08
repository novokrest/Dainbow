var gulp = require('gulp'),
    gutil = require('gulp-util'),
    jshint = require('gulp-jshint'),
    sass = require('gulp-sass'),
    sourcemaps = require('gulp-sourcemaps'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify');

var input = {
    html: 'source/**/*.html',
    css: 'source/css/**/*.css',
    js: 'source/js/**/*.js'
};

var output = {
    html: 'public',
    css: 'public/assets/stylesheets',
    js: 'public/assets/js'
};

gulp.task('copy-html', function() {
    return gulp.src(input.html)
               .pipe(gulp.dest(output.html));
});

gulp.task('jshint', function() {
    return gulp.src(input.js)
               .pipe(jshint())
               .pipe(jshint.reporter('jshint-stylish'));
});

gulp.task('build-css', function() {
    return gulp.src(input.css)
               .pipe(sourcemaps.init())
               .pipe(sass())
               .pipe(sourcemaps.write())
               .pipe(gulp.dest(output.css));
});

gulp.task('build-js', function() {
    return gulp.src(input.js)
               .pipe(sourcemaps.init())
               .pipe(concat('bundle.js'))
               .pipe(gutil.env.type == 'production' ? uglify() : gutil.noop())
               .pipe(sourcemaps.write())
               .pipe(gulp.dest(output.js));
});

gulp.task('watch', function() {
    gulp.watch(input.html, ['copy-html']);
    gulp.watch(input.js, ['jshint', 'build-js']);
    gulp.watch(input.css, ['build-css']);
});

gulp.task('default', ['watch'], function() {
    return gutil.log('Gulp is running!');
});