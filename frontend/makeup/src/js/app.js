import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import { render } from 'react-dom';
import { RouteService } from './routes';

const ListBooksComponent = require('./ListBooksComponent').ListBooksComponent;
const OverviewBookComponent = require('./OverviewBookComponent').OverviewBookComponent;
const LogReadActivityComponent = require('./LogReadActivityComponent').LogReadActivityComponent;
const AddBookForm = require('./add-book').AddBookForm;

const Header = () => (
    <nav className="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
        <button className="navbar-toggler navbar-toggler-right collapsed" 
                type="button" data-toggle="collapse" data-target="#navbarMenu" aria-controls="navbarMenu" 
                aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>
        <a className="navbar-brand" href="#">ReACT</a>
        <div id="navbarMenu" className="navbar-collapse collapse" aria-expanded="false">
            <ul className="navbar-nav mr-auto">
                <li className="nav-item active">
                    <Link className="nav-link" to={RouteService.getBooksRoute()}>Books</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to={RouteService.getAddBookRoute()}>Add</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to={RouteService.getRemoveBookRoute()}>Remove</Link>
                </li>
            </ul>
        </div>
    </nav>
)

const Main = () => (
    <main>
        <Switch>
            <Route exact path={RouteService.getBooksRoute()} component={ListBooksComponent} />
            <Route exact path={RouteService.getAddBookRoute()} component={AddBookForm} />
            <Route exact path={RouteService.getOverviewBookRoute()} component={OverviewBookComponent} />
            <Route exact path={RouteService.getLogReadActivityRoute()} component={LogReadActivityComponent} />
        </Switch>
    </main>
);

const App = () => (
    <div>
        <Header />
        <Main />
    </div>
);

render(
    <Router>
        <App />
    </Router>,
    document.getElementById('app')
);