import configuration from 'react-global-configuration';
import { browserHistory } from 'react-router';

configuration.set(window.__INITIAL_CONFIG__);
const baseApiUrl = configuration.get('server').rootUrl;

class RouteService {

    getBooksRoute() {
        return `${baseApiUrl}/books`;
    }

    navigateToBooksRoute(history) {
        history.push(this.getBooksRoute());
    }

    getAddBookRoute() {
        return `${baseApiUrl}/books/add`;
    }

    getRemoveBookRoute() {
        return `${baseApiUrl}/books/remove`;
    }

    getOverviewBookRoute(id) {
        const param = id ? id : ':id';
        return `${baseApiUrl}/books/${param}`;
    }

    navigateOverviewBookRoute(history, id) {
        history.push(this.getOverviewBookRoute(id));
    }

    getLogReadActivityRoute(id) {
        const param = id ? id : ':id';
        return `${baseApiUrl}/books/${param}/log-read-activity`
    }
};

module.exports = {
    RouteService: new RouteService()
};