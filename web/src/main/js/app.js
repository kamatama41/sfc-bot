import 'bootstrap/dist/css/bootstrap.css'

import client from './client'

import React from 'react'
import ReactDOM from 'react-dom'
import { Table } from 'reactstrap'
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
        <td>{this.props.game.release}</td>
        <td>{this.props.game.price}</td>
      </tr>
    )
  }
}

ReactDOM.render(
  <App />,
  document.getElementById('react')
)
