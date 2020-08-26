export class ListItem {

    title: string;
    path: string;
    url: string;
    description: string;

    constructor(data) {
      this.path = data.path;
      this.title = data.title;
      this.url = data.url;
      this.description = data.description;
    }
  }
