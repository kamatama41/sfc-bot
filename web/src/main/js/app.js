import 'bootstrap/dist/css/bootstrap.css'

import client from './client'

import React from 'react'
import ReactDOM from 'react-dom'
import {
  Table,
  Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink
} from 'reactstrap'
import {
  IntlProvider, FormattedDate, FormattedNumber
} from 'react-intl'
import ReactPaginate from 'react-paginate'


class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = {games: [], page: {}}
  }

  loadGames(pageNumber = 0) {
    client({method: 'GET', path: '/api/games', params: {page: pageNumber, size: 20}}).done(response => {
      this.setState({games: response.entity._embedded.games, page:response.entity.page})
    })
  }

  componentDidMount() {
    this.loadGames()
  }

  render() {
    return (
      <div>
        <Header />
        <ReactPaginate
          pageCount={this.state.page.totalPages}
          pageRangeDisplayed={5}
          marginPagesDisplayed={1}
          onPageChange={(data) => { this.loadGames(data.selected) }}
          containerClassName="pagination"
          pageClassName="page-item"
          pageLinkClassName="page-link"
          previousClassName="page-item"
          previousLinkClassName="page-link"
          nextClassName="page-item"
          nextLinkClassName="page-link"
          breakClassName="page-item"
          breakLabel={<div className="page-link">...</div>}
          activeClassName="active"
        />
        <GameList games={this.state.games}/>
      </div>
    )
  }
}

class Header extends React.Component {
  render() {
    return (
      <div>
        <Navbar color="faded" light toggleable>
          <NavbarBrand href="/">SFC Bot</NavbarBrand>
          <Nav className="ml-auto" navbar>
            <NavItem>
              <NavLink href="https://twitter.com/SuperFamicomBot" target="_blank">Twitter</NavLink>
            </NavItem>
            <NavItem>
              <NavLink href="https://github.com/kamatama41/sfc-bot" target="_blank">GitHub</NavLink>
            </NavItem>
          </Nav>
        </Navbar>
      </div>
    )
  }
}

class GameList extends React.Component {
  render() {
    return (
      <Table size="sm">
        <thead>
          <tr>
            <th>Title</th>
            <th>Publisher</th>
            <th>Release</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {this.props.games.map(game =>
            <Game key={game._links.self.href} game={game}/>
          )}
        </tbody>
      </Table>
    )
  }
}

class Game extends React.Component {
  render() {
    return (
      <tr>
        <td><a href={this.props.game.wikipediaUrl} target="_blank">{this.props.game.title}</a></td>
        <td>{this.props.game.publisher}</td>
        <td>
          <FormattedDate value={new Date(this.props.game.release)} year='numeric' month='long' day='numeric' />
        </td>
        <td>
          ¥<FormattedNumber value={this.props.game.price} />
        </td>
      </tr>
    )
  }
}

ReactDOM.render(
  <IntlProvider locale="ja">
    <App />
  </IntlProvider>,
  document.getElementById('react')
)
