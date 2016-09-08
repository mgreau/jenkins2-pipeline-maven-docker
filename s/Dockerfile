FROM alpine:3.3
MAINTAINER Dan ALLEN <http://mojavelinux.com>
MAINTAINER Damien DUPORTAL <damien.duportal@gmail.com>
MAINTAINER Maxime GREAU <greau.maxime@gmail.com>

ENV SLIDES_PATH /documents

# Global libs install
RUN apk --update add bash nodejs ruby ruby-io-console ruby-bundler git \
  && npm install -g gulp

RUN mkdir -p ${SLIDES_PATH}
WORKDIR "${SLIDES_PATH}"


# Quickstarter embeding
COPY build/Gemfile "${SLIDES_PATH}/Gemfile"
COPY slides/package.json "${SLIDES_PATH}/package.json"

ENV BUNDLE_GEMFILE "${SLIDES_PATH}/Gemfile"
ENV BUNDLE_PATH /bundle

RUN bundle install --system \
  && npm install \
  && rm -rf /var/cache/apk/*


# HTTP server
EXPOSE 8000
# Livereload server
EXPOSE 35729

CMD ["npm", "start"]
