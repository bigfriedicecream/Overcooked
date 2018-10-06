const uuidv4 = require('uuid/v4');

export function RecipeModel() {
    return {
        id: uuidv4(),
        title: '[title]',
        serves: 0,
        makes: 0,
        prepTime: 0,
        cookTime: 0,
        ings: [],
        method: []
    }
}