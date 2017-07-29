'use strict';

var rest = require('rest');
var defaultRequest = require('rest/interceptor/defaultRequest');
var mime = require('rest/interceptor/mime');
var registry = require('rest/mime/registry').child();
registry.register('application/hal+json', require('rest/mime/type/application/hal'));

module.exports = rest
    .wrap(require('rest/interceptor/errorCode'))
    .wrap(mime, { registry: registry })
    .wrap(defaultRequest, { headers: { 'Accept': 'application/hal+json' } });
