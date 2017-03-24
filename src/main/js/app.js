'use strict'

const React = require('react')
const ReactDOM = require('react-dom')
const client = require('./client')

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {games: []}
  }

  componentDidMount() {
    client({method: 'GET', path: '/api/games'}).done(response => {
      this.setState({games: response.entity._embedded.games})
    })
  }

  render() {
    return (
      <GameList games={this.state.games}/>
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
