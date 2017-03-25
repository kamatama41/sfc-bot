'use strict'

const React = require('react')
const ReactDOM = require('react-dom')
const ReactPaginate = require('react-paginate')
const client = require('./client')

class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = {games: [], page: {}}
  }

  loadGames(pageNumber = 0) {
    client({method: 'GET', path: '/api/games', params: {page: pageNumber, size: 15}}).done(response => {
      this.setState({games: response.entity._embedded.games, page:response.entity.page})
    })
  }

  componentDidMount() {
    this.loadGames()
  }

  render() {
    return (
      <div>
        <GameList games={this.state.games}/>
        <ReactPaginate
          pageCount={this.state.page.totalPages}
          pageRangeDisplayed={5}
          marginPagesDisplayed={2}
          onPageChange={(data) => { this.loadGames(data.selected) }}
        />
      </div>
    )
  }
}

class GameList extends React.Component {
  render() {
    return (
      <table>
        <tbody>
        <tr>
          <th>Title</th>
          <th>Publisher</th>
          <th>Release</th>
          <th>Price</th>
        </tr>
        {this.props.games.map(game =>
          <Game key={game._links.self.href} game={game}/>
        )}
        </tbody>
      </table>
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
