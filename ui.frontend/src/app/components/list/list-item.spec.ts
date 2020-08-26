import { ListItem } from './list-item';

describe('ListItem', () => {
  it('should create an instance', () => {
    const data = {
      path: '/content/wknd-spa-angular/us/en/home/page-1',
      description: null,
      url: '/content/wknd-spa-angular/us/en/home/page-1.html',
      title: 'Page 1'
    };
    expect(new ListItem(data)).toBeTruthy();
  });
});
