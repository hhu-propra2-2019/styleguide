const sharp = require('sharp');
const fs = require('fs');
const directory = '../img';
const destination = '../thumbs'
const main_picture = `elvis_young.jpg`


fs.readdirSync(directory).forEach(file => {
      sharp(`${directory}/${file}`)
    .resize(100, 100) // width, height
    .toFile(`${destination}/${file}`.substring(0, `${destination}/${file}`.length-4) + `-small.jpg`);
  });

sharp(`${directory}/${main_picture}`)
    .resize(null, 800)
    .toFile(`${destination}/${main_picture}`.substring(0, `${destination}/${main_picture}`.length-4) + `-small_main.jpg`);

