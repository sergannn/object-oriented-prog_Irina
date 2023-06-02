const { Sequelize, DataTypes } = require('sequelize');
const sequelize = new Sequelize('database_development', 'root', 'qwerty', {
  dialect: 'mysql',
  host: 'localhost'
});

const User = sequelize.define('User', {
  name: {
    type: DataTypes.STRING,
    allowNull: false
  },
  age: {
    type: DataTypes.INTEGER,
    allowNull: false
  }
});

module.exports = User;