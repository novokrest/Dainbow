import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import { render } from 'react-dom';
import { RouteService } from './routes';

const ListBooksComponent = require('./ListBooksComponent').ListBooksComponent;
const OverviewBookComponent = require('./OverviewBookComponent').OverviewBookComponent;
const LogReadActivityComponent = require('./LogReadActivityComponent').LogReadActivityComponent;
const AddBookComponent = require('./AddBookComponent').AddBookComponent;

const Header = () => (

    <div className="navbar-wrapper">
        <div className="container">
            <nav className="navbar navbar-inverse navbar-static-top">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span className="sr-only"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <a className="navbar-brand" href="#">ReACT</a>
                    </div>
                    <div id="navbar" className="navbar-collapse collapse">
                        <ul className="nav navbar-nav">
                            <li className="nav-item active">
                                <Link className="nav-link" to={RouteService.getBooksRoute()}>Books</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={RouteService.getAddBookRoute()}>Add</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={RouteService.getRemoveBookRoute()}>Remove</Link>
                            </li>
                            <li className="dropdown">
                                <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span className="caret"></span></a>
                                <ul className="dropdown-menu">
                                    <li><a href="#">TODO</a></li>
                                    <li role="separator" className="divider"></li>
                                    <li className="dropdown-header">Info</li>
                                    <li><a href="#">About</a></li>
                                    <li><a href="#">Help</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
)

const Main = () => (
    <main>
        <Switch>
            <Route exact path={RouteService.getBooksRoute()} component={ListBooksComponent} />
            <Route exact path={RouteService.getAddBookRoute()} component={AddBookComponent} />
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