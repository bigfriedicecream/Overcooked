import React from 'react';
import { Link } from 'react-router-dom';

const Root = () => {
    return (
        <div>
            <Link to={'./recipe/list'}>Recipe list</Link><br />
            <Link to={'./ingredient/list'}>Ingredient list</Link>
        </div>
    )
}

export default Root