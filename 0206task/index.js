const User = require('./models/User');

async function createUser() {
  try {
    await User.sync({ force: true });
    const user = await User.create({ name: 'John Doe', age: 25 });
    console.log(user.toJSON());
  } catch (error) {
    console.error('Error:', error);
  } finally {
    process.exit(); // Завершение процесса после выполнения
  }
}

createUser();
