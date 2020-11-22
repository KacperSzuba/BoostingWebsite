const startedLeagueImg = document.querySelector('.started-tier img');
const currentLeagueImg = document.querySelector('.current-tier img');
const desireLeagueImg = document.querySelector('.desire-tier img');
const startedLeague = document.querySelector('.started-tier .tier-division');
const currentLeague = document.querySelector('.current-tier .tier-division');
const desireLeague = document.querySelector('.desire-tier .tier-division');

window.addEventListener('load', () => {
    let startedTier = startedLeague.innerText.split(' ')[0];
    let startedDivision = startedLeague.innerText.split(' ')[1];

    let currentTier = currentLeague.innerText.split(' ')[0];
    let currentDivision = currentLeague.innerText.split(' ')[1];

    let desireTier = desireLeague.innerText.split(' ')[0];
    let desireDivision = desireLeague.innerText.split(' ')[1];

    startedLeagueImg.src = imagePath(startedTier, startedDivision);
    currentLeagueImg.src = imagePath(currentTier, currentDivision);
    desireLeagueImg.src = imagePath(desireTier, desireDivision);
});

function imagePath(tier, division) {
    return `${homeIMGURL}/${tier}/${tier}${convertDivision(division)}.png`;
}