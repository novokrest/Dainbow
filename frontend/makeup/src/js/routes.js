import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { RouteService } from './RouteService';
import Header from './Header'

const ListBooksComponent = require('./ListBooksComponent').ListBooksComponent;
const OverviewBookComponent = require('./OverviewBookComponent').OverviewBookComponent;
const LogReadActivityComponent = require('./LogReadActivityComponent').LogReadActivityComponent;
const ViewReadActivitiesComponent = require('./ViewReadActivitiesComponent').ViewReadActivitiesComponent;
const AddBookComponent = require('./AddBookComponent').AddBookComponent;
const RemoveBookComponent = require('./RemoveBookComponent').RemoveBookComponent;

const Main = () => (
    <main>
        <Switch>
            <Route exact path={RouteService.getBooksRoute()} component={ListBooksComponent} />
            <Route exact path={RouteService.getAddBookRoute()} component={AddBookComponent} />
            <Route exact path={RouteService.getRemoveBookRoute()} component={RemoveBookComponent} />
            <Route exact path={RouteService.getOverviewBookRoute()} component={OverviewBookComponent} />
            <Route exact path={RouteService.getLogReadActivityRoute()} component={LogReadActivityComponent} />
            <Route exact path={RouteService.getViewReadActivitiesRoute()} component={ViewReadActivitiesComponent} />
        </Switch>
    </main>
);

export default () => (
    <Router>
        <Header />
        <Main />
    </Router>
);
